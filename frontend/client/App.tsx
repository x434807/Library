// Author: Matúš Čongrády

import { BooksTable } from '@components/books/Books';
import { LoginPage } from '@components/LoginPage';
import { AppBar, Button, CssBaseline, Tab, Tabs, Toolbar, Typography } from '@material-ui/core';
import LibraryIcon from '@material-ui/icons/LocalLibrary';
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

const pathIndexes = {
  books: 0,
  customers: 1,
  loans: 2
};

const App = () => {
  const currentPath = window.location.hash.substring(1);

  const [, setSessionstoragePassword] = useSessionStorage(PASSWORD_SESSIONSTORAGE_KEY, '');
  const [, setSessionstorageLogin] = useSessionStorage(LOGIN_SESSIONSTORAGE_KEY, '');
  const [isLoggedIn, setIsLoggedIn] = useSessionStorage(IS_LOGGED_IN_SESSIONSTORAGE_KEY, false);
  const [isAdmin, setIsAdmin] = useSessionStorage(IS_ADMIN_SESSIONSTORAGE_KEY, false);
  const [seletedNavButton, setSelectedNavButton] = React.useState(pathIndexes[currentPath]);

  function logout() {
    setSessionstoragePassword('');
    setSessionstoragePassword('');
    setIsAdmin(false);
    setIsLoggedIn(false);
  }

  console.log(isAdmin);

  return (
    <div>
      <CssBaseline />
      <AppBar position="static" style={{ position: 'relative', paddingLeft: '3%', paddingRight: '3%' }}>
        <Toolbar>
          <LibraryIcon />
          <Typography variant="h5" color="inherit" noWrap style={{ paddingLeft: '15px' }}>
            Library information system
          </Typography>
          {isLoggedIn && (
            <Button
              onClick={logout}
              style={{ marginLeft: 'auto', color: 'white', borderColor: 'white' }}
              variant="outlined"
            >
              Logout
            </Button>
          )}
        </Toolbar>
        {isAdmin && (
          <Tabs style={{ paddingLeft: '2%' }} value={seletedNavButton}>
            <Tab
              label="Books"
              onClick={() => {
                setSelectedNavButton(0);
                location.hash = 'books';
              }}
            />
            <Tab
              label="Customers"
              onClick={() => {
                setSelectedNavButton(1);
                location.hash = 'customers';
              }}
            />
            <Tab
              label="Loans"
              onClick={() => {
                setSelectedNavButton(2);
                location.hash = 'loans';
              }}
            />
          </Tabs>
        )}
      </AppBar>
      <div style={{ paddingLeft: '5%', paddingRight: '5%', marginTop: '25px' }}>
        {isLoggedIn ? (
          <>
            {currentPath === 'books' && <BooksTable isAdmin={false} />}
            {isAdmin && currentPath === 'customers' && <BooksTable isAdmin={false} />}
            {isAdmin && currentPath === 'loans' && <BooksTable isAdmin={false} />}
          </>
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
