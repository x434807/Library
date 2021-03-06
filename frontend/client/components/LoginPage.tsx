// Author: Matúš Čongrády

import { Button, Card, FormHelperText, TextField } from '@material-ui/core';
import { ErrorMessage } from '@reusable/ErrorMessage';
import * as React from 'react';
import { checkCredentials } from '../controllers/customer-controller';

interface LoginPageProps {
  setSessionstorageLogin(login: string): void;
  setSessionstoragePassword(password: string): void;
  setIsLoggedIn(isLoggedIn: boolean): void;
  setIsAdmin(isAdmin: boolean): void;
}

export function LoginPage({
  setSessionstorageLogin,
  setSessionstoragePassword,
  setIsLoggedIn,
  setIsAdmin
}: LoginPageProps) {
  const [login, setLogin] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [isLoginTouched, setIsLoginTouched] = React.useState(false);
  const [isPasswordTouched, setIsPasswordTouched] = React.useState(false);
  const [error, setError] = React.useState(false);

  function handleSubmit(e) {
    e.preventDefault();
    checkCredentials(login, password)
      .then(res => {
        if (res.status === 'not ok') throw new Error('Wrong credentials');
        const isAdmin = res.admin === 'yes';
        setSessionstorageLogin(login);
        setSessionstoragePassword(password);
        setIsAdmin(isAdmin);
        setIsLoggedIn(true);

        window.location.hash = 'books';
      })
      .catch(() => {
        setError(true);
      });
  }

  const isLoginError = isLoginTouched && !login;
  const isPasswordError = isPasswordTouched && !password;

  return (
    <div
      style={{
        height: '60vh',
        display: 'flex',
        flexFlow: 'row wrap',
        justifyContent: 'center',
        alignItems: 'center'
      }}
    >
      <Card style={{ padding: '30px', width: '350px' }}>
        <p>Admin - login: test123, pw: hunter2</p>
        <p>Customer - login: repairman, pw: logmein</p>
        {error && <ErrorMessage message="Wrong login or password" />}
        <form autoComplete="off" onSubmit={handleSubmit}>
          <div>
            <TextField
              style={{ width: '100%' }}
              label="Login"
              value={login}
              error={isLoginError}
              onChange={e => {
                setLogin(e.target.value);
                setIsLoginTouched(true);
                setError(false);
              }}
              margin="normal"
            />
            {isLoginError && <FormHelperText>Login can not be empty</FormHelperText>}
          </div>
          &nbsp;&nbsp;
          <div>
            <TextField
              style={{ width: '100%' }}
              label="Password"
              value={password}
              error={isPasswordError}
              type="password"
              onChange={e => {
                setPassword(e.target.value);
                setIsPasswordTouched(true);
                setError(false);
              }}
              margin="normal"
            />
            <Button
              disabled={!password || !login}
              type="submit"
              fullWidth
              style={{ float: 'right', marginBottom: '50px', top: '15px' }}
              variant="contained"
              color="primary"
            >
              Submit
            </Button>
            {isPasswordError && <FormHelperText>Password can not be empty</FormHelperText>}
          </div>
        </form>
      </Card>
    </div>
  );
}
