import { Fab, Grid } from '@material-ui/core';
import TableCell from '@material-ui/core/TableCell';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import * as React from 'react';

interface ActionCellProps {
  onDelete(): void;
  onUpdate(): void;
}

export function ActionCell({ onUpdate, onDelete }: ActionCellProps) {
  return (
    <TableCell style={{ padding: '0px', paddingRight: '8px' }} numeric>
      <Grid justify="flex-end" container>
        <Grid item>
          <Fab onClick={onUpdate} style={{ width: '36px', height: '28px' }} variant="round">
            <EditIcon />
          </Fab>
        </Grid>
        <Grid item>
          <Fab onClick={onDelete} style={{ width: '36px', height: '28px' }} variant="round">
            <DeleteIcon />
          </Fab>
        </Grid>
      </Grid>
    </TableCell>
  );
}
