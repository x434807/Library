import { BooksTable } from '@components/books/Books';
import { AppBar, CssBaseline, Toolbar, Typography } from '@material-ui/core';
import LibraryIcon from '@material-ui/icons/LocalLibrary';
import { Router } from '@reach/router';
import * as React from 'react';
import { hot, setConfig as setHotLoaderConfig } from 'react-hot-loader';

setHotLoaderConfig({ pureSFC: true } as any);

const App = () => {
  return (
    <div>
      <CssBaseline />
      <AppBar position="static" style={{ position: 'relative', paddingLeft: '9%', paddingRight: '9%' }}>
        <Toolbar>
          <LibraryIcon />
          <Typography variant="h5" color="inherit" noWrap style={{ paddingLeft: '15px' }}>
            Library information system
          </Typography>
        </Toolbar>
      </AppBar>
      <Router>
        <BooksTable path="/books" />
      </Router>
    </div>
  );
};

export default hot(module)(App);
