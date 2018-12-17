// Author: Matúš Čongrády

import { REST_API_URL } from '@/contants';
import { request } from '@/utils/fetch';

export const getLoans = () => {
  return request(`${REST_API_URL}/loans`);
};

export const returnBook = (loanedBook: { bookId: string; returnCondition: string }) => {
  return request(`${REST_API_URL}/loans/returnBook`, { method: 'POST', body: JSON.stringify(loanedBook) });
};

export const loanBooks = (data: { customerLogin: string; bookIds: string[] }) => {
  return request(`${REST_API_URL}/loans/create`, { method: 'POST', body: JSON.stringify(data) });
};
