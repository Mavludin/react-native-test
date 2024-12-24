import { combineReducers, configureStore } from '@reduxjs/toolkit';

import AsyncStorage from '@react-native-async-storage/async-storage';

import {
  persistReducer,
  persistStore,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from 'redux-persist';

import income from './income';
import settings from './settings';
import userShifts from './userShifts';
import workShift from './workShift';

// Persist Config
const persistConfig = {
  key: 'root',
  storage: AsyncStorage,
  whitelist: [
    'income',
    'settings',
    'userShifts',
    'workShift',
  ],
};

export const rootReducer = combineReducers({
  income,
  settings,
  userShifts,
  workShift,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

export const persistor = persistStore(store);

