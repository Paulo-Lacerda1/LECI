import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image } from 'react-native';
import { Picker } from '@react-native-picker/picker';

export default function RegisterOptionsScreen({ navigation }) {
  const [accountType, setAccountType] = useState('User');

  const handleNext = () => {
    if (accountType === 'User') {
      navigation.navigate('RegisterUser');
    } else {
      navigation.navigate('RegisterProvider');
    }
  };

  return (
    <View style={styles.container}>
      <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
       <View style = {styles.headerRow}>
              <TouchableOpacity onPress = { () => navigation.goBack()} style = {styles.backButton}>
                <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
              </TouchableOpacity>
              <Text style={styles.title}>Create account</Text>
      </View>
      <Text style={styles.subtitle}>Register as</Text>

      <Picker
        selectedValue={accountType}
        style={styles.picker}
        onValueChange={(itemValue) => setAccountType(itemValue)}
      >
        <Picker.Item label="User" value="User" />
        <Picker.Item label="Service Provider" value="Service Provider" />
      </Picker>

      <TouchableOpacity style={styles.button} onPress={handleNext}>
        <Text style={styles.buttonText}>Continue</Text>
      </TouchableOpacity>

      
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
    backgroundColor: '#F5F5F5'
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    alignSelf: 'center',
    marginBottom: 20
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 10,
    textAlign: 'center'
  },
  subtitle: {
    marginBottom: 10
  },
  picker: {
    backgroundColor: '#fff',
    marginBottom: 20
  },

  button: {
    backgroundColor: '#222',
    padding: 10,
    borderRadius: 6,
    alignItems: 'center',
    marginTop: 8
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold'
  },
  headerRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'flex-start',
    width: '100%',
    paddingHorizontal: 20,
    marginBottom: 20
  }

});