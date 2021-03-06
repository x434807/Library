// Author: Matúš Čongrády

import { loanBooks, returnBook } from '@/controllers/loan-controller';
import {
  Button,
  Checkbox,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Divider,
  FormControl,
  FormHelperText,
  LinearProgress,
  MenuItem,
  Select,
  TextField,
  Typography
} from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { ActionCell } from '@reusable/ActionCell';
import { ErrorMessage } from '@reusable/ErrorMessage';
import * as React from 'react';
import { useBoolean } from 'react-hanger';
import { useList } from 'react-use';
import { createBook, deleteBook, editBook, getBooks } from '../../controllers/book-controller';

export function BooksTable({ isAdmin, login }: { isAdmin: boolean; login: string }) {
  const [selectedCells, { set, push }] = useList([]);
  const { setTrue: openDialog, setFalse: closeDialog, value: isDialogOpen } = useBoolean(false);
  const [isEditMode, setIsEditMode] = React.useState(false);
  const [editingBookId, setEditingBookId] = React.useState(null);

  const [name, setName] = React.useState('');
  const [author, setAuthor] = React.useState('');
  const [ISBN, setISBN] = React.useState('');
  const [isNameTouched, setNameTouched] = React.useState(false);
  const [isAuthorTouched, setAuthorTouched] = React.useState(false);
  const [isISBNTouched, setISBNTouched] = React.useState(false);
  const [bookStates, setBookState] = React.useState([]);
  const [data, setData] = React.useState(null);
  const [loading, setIsLoading] = React.useState(false);
  const [error, setIsError] = React.useState('');

  const fetchData = () => {
    setIsError('');
    setIsLoading(true);
    getBooks()
      .then(res => {
        setData(res);
        setIsLoading(false);
      })
      .catch(err => {
        setIsError(err);
      });
  };

  React.useEffect(
    () => {
      fetchData();
    },
    [0]
  );
  const isNameError = isNameTouched && !name;
  const isAuthorError = isAuthorTouched && !author;
  const isISBNError = isISBNTouched && !ISBN;

  if (data && !bookStates.length) setBookState(data.map(book => ({ id: book.id, condition: book.condition })));
  const selectCell = id => () => {
    if (selectedCells.includes(id)) {
      set(selectedCells.filter(cell => cell !== id));
    } else {
      push(id);
    }
  };

  const refetch = () => {
    closeDialog();
    resetFields();
    fetchData();
  };

  function loanSelectedBooks() {
    loanBooks({ customerLogin: login, bookIds: selectedCells }).then(refetch);
  }

  const handleChangeBookState = id => e => {
    const bookToAdjust = bookStates.find(book => book.id === id);
    setBookState([{ ...bookToAdjust, condition: e.target.value }, ...bookStates.filter(book => book.id !== id)]);
  };

  const handleReturnBook = id => () => {
    returnBook({ bookId: id, returnCondition: bookStates.find(book => book.id === id).condition }).then(refetch);
  };

  function handleSubmit() {
    if (isEditMode) {
      editBook({ name, author, isbn: ISBN }, editingBookId).then(refetch);
    } else {
      createBook({ name, author, isbn: ISBN }).then(refetch);
    }
  }

  const handleDelete = id => () => {
    deleteBook(id)
      .then(refetch)
      .catch(refetch);
  };

  const resetFields = () => {
    setEditingBookId(null);
    setNameTouched(false);
    setAuthorTouched(false);
    setISBNTouched(false);
    setIsEditMode(false);
    setName('');
    setAuthor('');
    setISBN('');
  };

  const makeCreateMode = () => {
    openDialog();
    resetFields();
  };

  const makeEditMode = entity => () => {
    openDialog();
    setEditingBookId(entity.id);
    setIsEditMode(true);
    setName(entity.name);
    setAuthor(entity.author);
    setISBN(entity.isbn);
  };

  return (
    <>
      {loading && <LinearProgress />}
      {error && <ErrorMessage message="Error" />}
      {data && bookStates.length && (
        <>
          <Paper>
            <Typography variant="h6" color="inherit" noWrap style={{ paddingLeft: '18px', paddingTop: '12px' }}>
              Available Books
            </Typography>
            <Table padding="dense">
              <TableHead>
                <TableRow>
                  {!isAdmin && <TableCell />}
                  <TableCell>Name</TableCell>
                  <TableCell>Author</TableCell>
                  <TableCell>ISBN</TableCell>
                  <TableCell>Condition</TableCell>
                  {isAdmin && <TableCell>Available</TableCell>}
                  {isAdmin && <TableCell>Borrowed by</TableCell>}
                  {isAdmin && <TableCell>Actions</TableCell>}
                </TableRow>
              </TableHead>
              <TableBody>
                {data
                  .filter(book => book.available || isAdmin)
                  .map(entity => (
                    <TableRow key={entity.id}>
                      {!isAdmin && (
                        <TableCell padding="none" component="th" scope="row">
                          <Checkbox checked={selectedCells.includes(entity.id)} onClick={selectCell(entity.id)} />
                        </TableCell>
                      )}
                      <TableCell padding="dense" component="th" scope="row">
                        {entity.name}
                      </TableCell>
                      <TableCell>{entity.author}</TableCell>
                      <TableCell>{entity.isbn}</TableCell>
                      <TableCell>{entity.condition}</TableCell>
                      {isAdmin && <TableCell>{String(entity.available)}</TableCell>}
                      {isAdmin && (
                        <TableCell>
                          {entity.customer ? `${entity.customer.name} ${entity.customer.surname}` : '-'}
                        </TableCell>
                      )}
                      {isAdmin && <ActionCell onDelete={handleDelete(entity.id)} onUpdate={makeEditMode(entity)} />}
                    </TableRow>
                  ))}
              </TableBody>
            </Table>
          </Paper>
          {!isAdmin && (
            <Button
              onClick={loanSelectedBooks}
              style={{ float: 'right', marginBottom: '50px', top: '15px' }}
              variant="contained"
              color="primary"
            >
              Loan selected books
            </Button>
          )}
          {isAdmin && (
            <Button
              onClick={makeCreateMode}
              style={{ float: 'right', marginBottom: '50px', top: '15px' }}
              variant="contained"
              color="primary"
            >
              Add new
            </Button>
          )}
          <Dialog
            PaperProps={{ style: { maxWidth: '1200px', backgroundColor: 'rgb(238, 238, 238)' } }}
            open={isDialogOpen}
            onClose={closeDialog}
            aria-labelledby="form-dialog-title"
          >
            <DialogTitle id="form-dialog-title">
              <Typography variant="h5">{isEditMode ? 'Edit book' : 'Create new Book'}</Typography>
            </DialogTitle>
            <DialogContent style={{ minWidth: '450px' }}>
              <FormControl style={{ marginTop: '6px' }} fullWidth>
                <div>
                  <TextField
                    inputProps={{
                      autocomplete: 'off',
                      autocorrect: 'off',
                      spellcheck: 'false'
                    }}
                    autoFocus
                    value={name}
                    margin="dense"
                    error={isNameError}
                    label="Name"
                    type="string"
                    fullWidth
                    onChange={e => {
                      setName(e.target.value);
                      setNameTouched(true);
                    }}
                  />
                  {isNameError && (
                    <FormHelperText error>Name can not be empty and must be atleast 5 chars</FormHelperText>
                  )}
                </div>
                <div>
                  <TextField
                    inputProps={{
                      autocomplete: 'off',
                      autocorrect: 'off',
                      spellcheck: 'false'
                    }}
                    value={author}
                    type="string"
                    margin="dense"
                    error={isAuthorError}
                    label="Author"
                    fullWidth
                    onChange={e => {
                      setAuthor(e.target.value);
                      setAuthorTouched(true);
                    }}
                  />
                  {isAuthorError && (
                    <FormHelperText error>Author can not be empty and must be atleast 5 chars</FormHelperText>
                  )}
                </div>
                <div>
                  <TextField
                    inputProps={{
                      autocomplete: 'off',
                      autocorrect: 'off',
                      spellcheck: 'false'
                    }}
                    value={ISBN}
                    type="string"
                    margin="dense"
                    label="ISBN"
                    error={isISBNError}
                    fullWidth
                    onChange={e => {
                      setISBN(e.target.value);
                      setISBNTouched(true);
                    }}
                  />
                  {isISBNError && (
                    <FormHelperText error>ISBN can not be empty and must be atleast 5 chars</FormHelperText>
                  )}
                </div>
              </FormControl>
            </DialogContent>
            <DialogActions style={{ padding: '5px' }}>
              <Button onClick={closeDialog} color="primary">
                Cancel
              </Button>
              <Button disabled={!name || !author || !ISBN} onClick={handleSubmit} variant="contained" color="primary">
                Save
              </Button>
            </DialogActions>
          </Dialog>
          {!isAdmin && (
            <>
              <Divider />
              <Paper style={{ marginTop: '70px' }}>
                <Typography variant="h6" color="inherit" noWrap style={{ paddingLeft: '18px', paddingTop: '12px' }}>
                  Books you have loaned
                </Typography>
                <Table padding="dense">
                  <TableHead>
                    <TableRow>
                      <TableCell>Name</TableCell>
                      <TableCell>Author</TableCell>
                      <TableCell>ISBN</TableCell>
                      <TableCell>Condition</TableCell>
                      {!isAdmin && <TableCell>Return condition</TableCell>}
                      {!isAdmin && <TableCell />}
                      {isAdmin && <TableCell>Actions</TableCell>}
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {data
                      .filter(book => book.customer)
                      .filter(book => book.customer.login === login)
                      .map(entity => (
                        <TableRow key={entity.id}>
                          <TableCell padding="dense" component="th" scope="row">
                            {entity.name}
                          </TableCell>
                          <TableCell>{entity.author}</TableCell>
                          <TableCell>{entity.isbn}</TableCell>
                          <TableCell>{entity.condition}</TableCell>
                          <TableCell>
                            <Select
                              value={bookStates.find(b => b.id === entity.id).condition}
                              onChange={handleChangeBookState(entity.id)}
                            >
                              <MenuItem value={'NEW'}>NEW</MenuItem>
                              <MenuItem value={'GOOD'}>GOOD</MenuItem>
                              <MenuItem value={'BAD'}>BAD</MenuItem>
                            </Select>
                          </TableCell>
                          <Button
                            color="primary"
                            onClick={handleReturnBook(entity.id)}
                            style={{
                              width: '120px',
                              height: '28px',
                              float: 'right',
                              marginRight: '10px',
                              marginTop: '5px'
                            }}
                            variant="raised"
                          >
                            Return
                          </Button>
                        </TableRow>
                      ))}
                  </TableBody>
                </Table>
              </Paper>
            </>
          )}
        </>
      )}
    </>
  );
}
