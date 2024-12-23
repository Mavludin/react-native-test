import { createSlice } from '@reduxjs/toolkit';

export interface ShiftsState {
  data: any
  flag: boolean
}

const initialState: ShiftsState = {
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

export const shiftsSlice = createSlice({
  name: 'shifts',
  initialState,
  reducers: {},
});

export default shiftsSlice.reducer;
