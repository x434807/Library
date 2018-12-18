// Author: Matúš Čongrády

import { REST_API_URL } from '@/contants';
import { request } from '@/utils/fetch';

export const getCustomers = () => {
  return request(`${REST_API_URL}/customers`);
};

export const checkCredentials = (login, password) => {
  return request(`${REST_API_URL}/customers/login`, {
    method: 'POST',
    body: JSON.stringify({ login, password })
  })
    .then(res => {
      if (res.successfull !== 'yes') throw new Error('Error');
      return res;
    })
    .catch(res => {
      return res;
    });
};
