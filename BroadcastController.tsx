import React, { useEffect } from 'react';

import { NativeEventEmitter } from 'react-native';

const eventEmitter = new NativeEventEmitter();

export const BroadcastController = React.memo(() => {
  useEffect(() => {
    const subscription = eventEmitter.addListener('onRequestReceived', (requestData) => {
      console.log({ requestData });
    });

    return () => subscription.remove();
  }, []);

  return null;
});
