import { createSlice } from '@reduxjs/toolkit';

export interface SettingsState {
  theme: string
  weekNumbers: string
  firstDayOfWeek: number
  aspectRatioMultiplier: number
  country: string
  region: string | null
  prepare: boolean
  showShiftTitle: number
  showShiftTitleIfNoDuration: boolean
  showShiftDuration: boolean
  lastError: string | null
}

export const settingsInitialState: SettingsState = {
  theme: 'default',
  weekNumbers: 'none',
  firstDayOfWeek: 7,
  aspectRatioMultiplier: 1.6,
  country: 'US',
  region: null,
  prepare: true,
  showShiftTitle: 1,
  showShiftTitleIfNoDuration: false,
  showShiftDuration: false,
  lastError: null,
};

export const settingsSlice = createSlice({
  name: 'settings',
  initialState: settingsInitialState,
  reducers: {},
});

export default settingsSlice.reducer;
