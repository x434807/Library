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
import { useAsync, useList } from 'react-use';
import { createBook, deleteBook, editBook, getBooks } from '../../controllers/book-controller';

function reload() {
  location.reload();
}

export function BooksTable({ isAdmin, login }: { isAdmin: boolean; login: string }) {
  const { value: data, loading, error } = useAsync(getBooks, 0);
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

  const isNameError = isNameTouched && !name;
  const isAuthorError = isAuthorTouched && !author;
  const isISBNError = isISBNTouched && !ISBN;

  const selectCell = id => () => {
    if (selectedCells.includes(id)) {
      set(selectedCells.filter(cell => cell !== id));
    } else {
      push(id);
    }
  };

  function loanSelectedBooks() {
    loanBooks({ customerLogin: login, bookIds: selectedCells }).then(reload);
  }

  const handleReturnBook = id => () => {
    returnBook({ bookId: id, returnCondition: 'GOOD' }).then(reload);
  };

  function handleSubmit() {
    if (isEditMode) {
      editBook({ name, author, isbn: ISBN }, editingBookId).then(reload);
    } else {
      createBook({ name, author, isbn: ISBN }).then(reload);
    }
  }

  const handleDelete = id => () => {
    deleteBook(id)
      .then(reload)
      .catch(reload);
  };

  const makeCreateMode = () => {
    openDialog();
    setEditingBookId(null);
    setNameTouched(false);
    setAuthorTouched(false);
    setISBNTouched(false);
    setIsEditMode(false);
    setName('');
    setAuthor('');
    setISBN('');
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
      {data && (
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
                  <TableCell numeric>Author</TableCell>
                  <TableCell numeric>ISBN</TableCell>
                  <TableCell numeric>Condition</TableCell>
                  {isAdmin && <TableCell numeric>Available</TableCell>}
                  {isAdmin && <TableCell numeric>Borrowed by</TableCell>}
                  {isAdmin && <TableCell numeric>Actions</TableCell>}
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
                      <TableCell numeric>{entity.author}</TableCell>
                      <TableCell numeric>{entity.isbn}</TableCell>
                      <TableCell numeric>{entity.condition}</TableCell>
                      {isAdmin && <TableCell numeric>{String(entity.available)}</TableCell>}
                      {isAdmin && (
                        <TableCell numeric>
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
                      {!isAdmin && <TableCell />}
                      <TableCell>Name</TableCell>
                      <TableCell numeric>Author</TableCell>
                      <TableCell numeric>ISBN</TableCell>
                      <TableCell numeric>Condition</TableCell>
                      <TableCell numeric>Borrowed by</TableCell>
                      {isAdmin && <TableCell numeric>Actions</TableCell>}
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
                          <TableCell numeric>{entity.author}</TableCell>
                          <TableCell numeric>{entity.isbn}</TableCell>
                          <TableCell numeric>{entity.condition}</TableCell>
                          <TableCell numeric>
                            {entity.customer ? `${entity.customer.name} ${entity.customer.surname}` : '-'}
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
