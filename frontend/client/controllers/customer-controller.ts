// Author: Matúš Čongrády

import { REST_API_URL } from '@/contants';
import { request } from '@/utils/fetch';

export const getCustomers = () => {
  return request(`${REST_API_URL}/customers`);
};
