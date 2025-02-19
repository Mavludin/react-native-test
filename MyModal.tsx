import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { Modal } from 'react-native-paper';

type Props = {
  visible: boolean
  onCancel: () => void
  onConfirm: () => void
}

export const MyModal = ({ visible, onCancel, onConfirm }: Props) => {
  return (
    <Modal
      visible={visible}
      onDismiss={onCancel}
      contentContainerStyle={styles.contentContainerStyle}
    >
      <View style={styles.header}>
        <Text>Data request</Text>
        <Text>Would you like to send data?</Text>
      </View>

      <View style={styles.buttons}>
        <TouchableOpacity
          onPress={onConfirm}
          style={styles.buttonOk}
        >
          <Text>Send</Text>
        </TouchableOpacity>
        <TouchableOpacity
          onPress={onCancel}
          style={styles.buttonCancel}
        >
          <Text>Cancel</Text>
        </TouchableOpacity>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  contentContainerStyle: {
    backgroundColor: 'white',
    padding: 20,
    margin: 20,
  },
  header: {},
  buttons: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: 10,
  },
  buttonOk: {
    paddingHorizontal: 5,
    backgroundColor: '#b6e61c',
  },
  buttonCancel: {
    paddingHorizontal: 5,
    backgroundColor: '#f43f35',
  },
});
