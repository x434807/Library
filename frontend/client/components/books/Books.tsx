import { Button, Checkbox, LinearProgress } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { ActionCell } from '@reusable/ActionCell';
import { ErrorMessage } from '@reusable/ErrorMessage';
import * as React from 'react';
import { useAsync, useList } from 'react-use';
import { getBooks } from './book-controller';

export function BooksTable({ path, isAdmin }: { path: string; isAdmin: boolean }) {
  const { value: data, loading, error } = useAsync(getBooks, 0);
  const [selectedCells, { set, push }] = useList([]);

  const selectCell = id => () => {
    if (selectedCells.includes(id)) {
      set(selectedCells.filter(cell => cell !== id));
    } else {
      push(id);
    }
  };

  function loanSelectedBooks() {
    console.log(selectedCells);
  }

  return (
    <Paper>
      {loading && <LinearProgress />}
      {error && <ErrorMessage message={error} />}
      {data && (
        <>
          <Table padding="dense">
            <TableHead>
              <TableRow>
                {!isAdmin && <TableCell />}
                <TableCell>Name</TableCell>
                <TableCell numeric>Author</TableCell>
                <TableCell numeric>ISBN</TableCell>
                <TableCell numeric>Condition</TableCell>
                <TableCell numeric>Available</TableCell>
                <TableCell numeric>Borrowed by</TableCell>
                {isAdmin && <TableCell numeric>Actions</TableCell>}
              </TableRow>
            </TableHead>
            <TableBody>
              {data.map(entity => (
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
                  <TableCell numeric>{String(entity.available)}</TableCell>
                  <TableCell numeric>
                    {entity.customer ? `${entity.customer.name} ${entity.customer.surname}` : '-'}
                  </TableCell>
                  {isAdmin && <ActionCell onDelete={console.log} onUpdate={console.log} />}
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <Button
            onClick={loanSelectedBooks}
            style={{ float: 'right', marginBottom: '5px', top: '15px' }}
            variant="contained"
            color="primary"
          >
            Loan selected books
          </Button>
        </>
      )}
    </Paper>
  );
}
