import { Card, TextField } from '@material-ui/core';
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

  function handleSubmit(e) {
    e.preventDefault();
    setSessionstorageLogin(login);
    setSessionstoragePassword(password);
    setIsAdmin(true);
    setIsLoggedIn(true);
  }

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
          <TextField
            style={{ width: '100%' }}
            label="Login"
            value={login}
            onChange={e => setLogin(e.target.value)}
            margin="normal"
          />
          &nbsp;&nbsp;
          <TextField
            style={{ width: '100%' }}
            label="Password"
            value={password}
            type="password"
            onChange={e => setPassword(e.target.value)}
            margin="normal"
          />
          <button type="submit" hidden />
        </form>
      </Card>
    </div>
  );
}
