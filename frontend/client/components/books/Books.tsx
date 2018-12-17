import { LinearProgress } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { ActionCell } from '@reusable/ActionCell';
import { ErrorMessage } from '@reusable/ErrorMessage';
import * as React from 'react';
import { useAsync } from 'react-use';
import { getBooks } from './book-controller';

export function BooksTable({ path, isAdmin }: { path: string; isAdmin: boolean }) {
  const { value: data, loading, error } = useAsync(getBooks, 0);

  return (
    <Paper>
      {loading && <LinearProgress />}
      {error && <ErrorMessage message={error} />}
      {data && (
        <Table padding="dense">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell numeric>Author</TableCell>
              <TableCell numeric>ISBN</TableCell>
              <TableCell numeric>Condition</TableCell>
              <TableCell numeric>Available</TableCell>
              <TableCell numeric>Borrowed by</TableCell>
              <TableCell numeric>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {data.map(entity => (
              <TableRow key={entity.id}>
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
                <ActionCell onDelete={console.log} onUpdate={console.log} />
              </TableRow>
            ))}
          </TableBody>
        </Table>
      )}
    </Paper>
  );
}
