import { useCallback, useMemo } from 'react';
import { NativeModules } from 'react-native';

const { BroadcastSender } = NativeModules;

export const useBroadcast = () => {
  const sendBroadcast = useCallback((data: unknown) => {

    const json = JSON.stringify(data);

    BroadcastSender.sendBroadcast(json);
  }, []);


  return useMemo(() => ({ sendBroadcast }), []);
};
