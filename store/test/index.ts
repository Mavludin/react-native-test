import { createSlice } from '@reduxjs/toolkit';

export interface TestState {
  data: any
  flag: boolean
}

const initialState: TestState = {
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

export const testSlice = createSlice({
  name: 'test',
  initialState,
  reducers: {},
});

export default testSlice.reducer;
