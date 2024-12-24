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

import userShifts from './userShifts';
import workShift from './workShift';
import income from './income';

// Persist Config
const persistConfig = {
  key: 'root',
  storage: AsyncStorage,
  whitelist: ['userShifts', 'workShift', 'income'],
};

export const rootReducer = combineReducers({
  income,
  workShift,
  userShifts,
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

