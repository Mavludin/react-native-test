/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useEffect } from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import { Provider as ReduxProvider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

import { PaperProvider } from 'react-native-paper';
import { BroadcastController } from './BroadcastController';

import { persistor, store } from './store';
import AsyncStorage from '@react-native-async-storage/async-storage';

type SectionProps = PropsWithChildren<{
  title: string;
}>;

function Section({children, title}: SectionProps): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
}

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  useEffect(() => {
    const getData = async () => {
      const storeString = await AsyncStorage.getItem('persist:root');

      if (storeString === null) return

      const data = JSON.parse(storeString);

      console.log({ storeString: JSON.parse(data.test) });
    };

    getData();
  }, []);

  return (
    <ReduxProvider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <PaperProvider>
          <SafeAreaView style={backgroundStyle}>
            <StatusBar
              barStyle={isDarkMode ? 'light-content' : 'dark-content'}
              backgroundColor={backgroundStyle.backgroundColor}
            />

            <ScrollView
              contentInsetAdjustmentBehavior="automatic"
              style={backgroundStyle}>
              <Header />
              <View
                style={{
                  backgroundColor: isDarkMode ? Colors.black : Colors.white,
                }}>
                <Section title="Step One">
                  Edit <Text style={styles.highlight}>App.tsx</Text> to change this
                  screen and then come back to see your edits.
                </Section>
                <Section title="See Your Changes">
                  <ReloadInstructions />
                </Section>
                <Section title="Debug">
                  <DebugInstructions />
                </Section>
                <Section title="Learn More">
                  Read the docs to discover what to do next:
                </Section>
                <LearnMoreLinks />
              </View>
            </ScrollView>

            <BroadcastController />
          </SafeAreaView>
        </PaperProvider>
      </PersistGate>
    </ReduxProvider>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
