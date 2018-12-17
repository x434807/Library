// Author: Matúš Čongrády

const getHeaders = () => {
  return {
    'x-http-login': sessionStorage.getItem('login'),
    'x-http-password': sessionStorage.getItem('pw'),
    'Content-type': 'application/json'
  };
};

export const request = (requestInfo: RequestInfo, init?: RequestInit) => {
  return fetch(
    requestInfo,
    init ? { ...init, headers: { ...getHeaders(), ...init.headers } } : { headers: { ...getHeaders() } }
  )
    .then(res => res.json())
    .catch(err => {
      console.error(err);
      throw err;
    });
};
