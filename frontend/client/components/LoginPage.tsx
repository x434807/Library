// Author: Matúš Čongrády

import { Card, FormHelperText, TextField } from '@material-ui/core';
import * as React from 'react';

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

  function handleSubmit(e) {
    e.preventDefault();
    setSessionstorageLogin(login);
    setSessionstoragePassword(password);
    setIsAdmin(true);
    setIsLoggedIn(true);

    const isAdmin = false;
    if (!isAdmin) {
      window.location.hash = 'books';
    }
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
              }}
              margin="normal"
            />
            <button disabled={!password || !login} type="submit" hidden />
            {isPasswordError && <FormHelperText>Password can not be empty</FormHelperText>}
          </div>
        </form>
      </Card>
    </div>
  );
}
