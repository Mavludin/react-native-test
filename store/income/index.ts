import { createSlice } from '@reduxjs/toolkit';

export interface IncomeState {
  data: any
  flag: boolean
}

const initialState: IncomeState = {
  data: {
    111: [1, 2, 3, 4, 5],
    222: ['333', '222', '555'],
    333: {
      one: 'one',
      two: 'two',
    },
  },
  flag: false,
};

export const incomeSlice = createSlice({
  name: 'income',
  initialState,
  reducers: {},
});

export default incomeSlice.reducer;
