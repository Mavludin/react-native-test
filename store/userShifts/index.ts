import { createSlice } from '@reduxjs/toolkit';

export interface UserShiftsState {
  list: any[]
}

const initialState: UserShiftsState = {
  list: [
    {
      'id': 0,
      'hidden': true,
      'title': 'Work',
      'color': '#c8e6c9',
      'workTime': 480,
    },
    {
      'id': 1,
      'hidden': true,
      'title': 'Day Off',
      'color': 'transparent',
      'workTime': 0,
    },
    {
      'id': 2,
      'title': 'Work1',
      'color': '#c8e6c9',
      'workTime': 480,
      'isHashShift': true,
    },
    {
      'id': 3,
      'title': 'Day Off',
      'color': 'transparent',
      'workTime': 0,
    },
    {
      'id': 4,
      'title': 'Morning',
      'color': '#ffe0b2',
      'workTime': 480,
    },
    {
      'id': 5,
      'title': 'Day',
      'color': '#b2dfdb',
      'workTime': 480,
    },
    {
      'id': 6,
      'title': 'Evening',
      'color': '#d1c4e9',
      'workTime': 480,
    },
    {
      'id': 7,
      'title': 'Night',
      'color': '#9fa8da',
      'workTime': 480,
    },
    {
      'id': 8,
      'title': 'Vacation',
      'color': 'transparent',
      'workTime': 0,
    },
  ],
};

export const useShiftsSlice = createSlice({
  name: 'userShifts',
  initialState,
  reducers: {},
});

export default useShiftsSlice.reducer;
