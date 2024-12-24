import { createSlice } from '@reduxjs/toolkit';

export interface WorkShiftState {
  active: number
  list: any[]
}

const initialState: WorkShiftState = {
  active: 0,
  list: [
    {
      shift: {
        array: [
          2,
          3,
          3,
          3,
        ],
        options: {
          useNotWorkingDays: false,
        },
      },
      start: '2024-12-02',
      end: null,
      title: 'new',
      income: {
        period: 'month',
      },
      hash: {
        '2024-12-17': 2,
        '2024-12-01': 2,
      },
      overtime: {
        '2024-12-17': {
          time: 30,
          income: 0,
        },
      },
      manualOnly: false,
      reminders: null,
      alarm: true,
    },
    {
      shift: {
        array: [
          2,
          3,
          3,
          3,
        ],
        options: {
          useNotWorkingDays: false,
        },
      },
      start: null,
      end: null,
      title: 'new 1',
      income: {
        period: 'month',
      },
      hash: {},
      overtime: {},
      manualOnly: false,
      reminders: null,
      alarm: true,
      viewable: false,
    },
  ],
};

export const workShiftSlice = createSlice({
  name: 'workShift',
  initialState,
  reducers: {},
});

export default workShiftSlice.reducer;
