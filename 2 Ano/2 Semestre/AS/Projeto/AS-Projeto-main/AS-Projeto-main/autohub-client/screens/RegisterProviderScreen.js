import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, TouchableOpacity, Image } from 'react-native';

export default function RegisterProviderScreen({ navigation }) {
  const [form, setForm] = useState({
    businessName: '',
    email: '',
    password: '',
    confirm: '',
    nif: '',
    phone: '',
    address: ''
  });

  const handleChange = (field, value) => {
    setForm({ ...form, [field]: value });
  };

  const handleSubmit = () => {
    navigation.navigate('EmailConfirmation');
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

      {['businessName','email','password','confirm','nif','phone','address'].map(field => (
        <TextInput
          key={field}
          style={styles.input}
          placeholder={field.charAt(0).toUpperCase() + field.slice(1)}
          secureTextEntry={field.includes('password')}
          onChangeText={text => handleChange(field, text)}
        />
      ))}

       <TouchableOpacity style={styles.button} onPress={handleSubmit}>
              <Text style={styles.buttonText}>Register</Text>
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
    marginTop: 30,
    marginBottom: 20,
    alignSelf: 'center',
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center'
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    borderRadius: 6,
    marginBottom: 10,
    backgroundColor: '#fff'
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