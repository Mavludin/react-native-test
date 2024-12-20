import React, { useEffect } from 'react';

import { AppRegistry } from 'react-native';

export const BroadcastController = React.memo(() => {
  useEffect(() => {
    AppRegistry.registerHeadlessTask('dataRequest', () => async (data: string) => {
      console.log('request received', JSON.parse(data));

      // Here we automatically send data if needed
    });
  }, []);

  return null;
});
