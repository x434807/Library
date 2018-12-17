// Author: Andrej Sokolik
import { getLoans } from '@/controllers/loan-controller';
import { LinearProgress, Typography } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { ErrorMessage } from '@reusable/ErrorMessage';
import * as React from 'react';
import { useAsync } from 'react-use';

export function LoansTable() {
  const { value: data, loading, error } = useAsync(getLoans, 0);

  return (
    <>
      {loading && <LinearProgress />}
      {error && <ErrorMessage message="Error" />}
      {data && (
        <>
          <Paper>
            <Typography variant="h6" color="inherit" noWrap style={{ paddingLeft: '18px', paddingTop: '12px' }}>
              Customers
            </Typography>
            <Table padding="dense">
              <TableHead>
                <TableRow>
                  <TableCell>Customer</TableCell>
                  <TableCell>Loaned at</TableCell>
                  <TableCell>Books</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {data.map(entity => (
                  <TableRow key={entity.id}>
                    <TableCell>
                      {entity.customer.name}&nbsp;{entity.customer.surname}
                    </TableCell>
                    <TableCell>{new Date(entity.timestamp).toLocaleString()}</TableCell>
                    <TableCell>{entity.items.map(item => item.book.name).join(' | ')}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </Paper>
        </>
      )}
    </>
  );
}
