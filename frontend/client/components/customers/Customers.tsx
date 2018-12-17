// Author: Martin Piatka
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
import { getCustomers } from '../../controllers/customer-controller';

export function CustomersTable() {
  const { value: data, loading, error } = useAsync(getCustomers, 0);

  console.log(data);

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
                  <TableCell numeric>ID</TableCell>
                  <TableCell>Name</TableCell>
                  <TableCell>Surname</TableCell>
                  <TableCell>Login</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {data.map(entity => (
                  <TableRow key={entity.id}>
                    <TableCell padding="dense" component="th" scope="row">
                      {entity.id}
                    </TableCell>
                    <TableCell numeric>{entity.name}</TableCell>
                    <TableCell numeric>{entity.surname}</TableCell>
                    <TableCell numeric>{entity.login}</TableCell>
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
