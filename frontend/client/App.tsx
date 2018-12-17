import { BooksTable } from '@components/books/Books';
import { LoginPage } from '@components/LoginPage';
import { AppBar, CssBaseline, Toolbar, Typography } from '@material-ui/core';
import LibraryIcon from '@material-ui/icons/LocalLibrary';
import { Router } from '@reach/router';
import * as React from 'react';
import { hot, setConfig as setHotLoaderConfig } from 'react-hot-loader';
import { useSessionStorage } from 'react-use';
import {
  IS_ADMIN_SESSIONSTORAGE_KEY,
  IS_LOGGED_IN_SESSIONSTORAGE_KEY,
  LOGIN_SESSIONSTORAGE_KEY,
  PASSWORD_SESSIONSTORAGE_KEY
} from './contants';

setHotLoaderConfig({ pureSFC: true } as any);

const App = () => {
  const [, setSessionstoragePassword] = useSessionStorage(PASSWORD_SESSIONSTORAGE_KEY, '');
  const [, setSessionstorageLogin] = useSessionStorage(LOGIN_SESSIONSTORAGE_KEY, '');
  const [isLoggedIn, setIsLoggedIn] = useSessionStorage(IS_LOGGED_IN_SESSIONSTORAGE_KEY, false);
  const [isAdmin, setIsAdmin] = useSessionStorage(IS_ADMIN_SESSIONSTORAGE_KEY, false);

  return (
    <div>
      <CssBaseline />
      <AppBar position="static" style={{ position: 'relative', paddingLeft: '3%', paddingRight: '3%' }}>
        <Toolbar>
          <LibraryIcon />
          <Typography variant="h5" color="inherit" noWrap style={{ paddingLeft: '15px' }}>
            Library information system
          </Typography>
        </Toolbar>
      </AppBar>
      <div style={{ position: 'relative', paddingLeft: '5%', paddingRight: '5%', marginTop: '25px' }}>
        {isLoggedIn ? (
          <Router>
            <BooksTable isAdmin={isAdmin} path="/books" />
          </Router>
        ) : (
          <LoginPage
            setSessionstorageLogin={setSessionstorageLogin}
            setSessionstoragePassword={setSessionstoragePassword}
            setIsLoggedIn={setIsLoggedIn}
            setIsAdmin={setIsAdmin}
          />
        )}
      </div>
    </div>
  );
};

export default hot(module)(App);
