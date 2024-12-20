import React, { useEffect } from 'react';

import { AppRegistry } from 'react-native';
import { useBroadcast } from './useBroadcast';

export const BroadcastController = React.memo(() => {
  const { sendBroadcast } = useBroadcast();

  useEffect(() => {
    AppRegistry.registerHeadlessTask('DataRequest', () => async (data: string) => {
      console.log('request received', data);

      // Here we automatically send data if needed
      sendBroadcast({ test: 'test data' });
    });
  }, []);

  return null;
});
