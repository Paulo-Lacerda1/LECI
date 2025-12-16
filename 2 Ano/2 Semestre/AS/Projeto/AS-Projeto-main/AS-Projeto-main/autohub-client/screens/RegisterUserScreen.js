import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Image } from 'react-native';

import AsyncStorage from '@react-native-async-storage/async-storage';

export default function RegisterUserScreen({ navigation }) {
  const [form, setForm] = useState({
    firstName: '',
    lastName: '',
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

  const handleSubmit = async () => {
    // Verifica se todos os campos (exceto address) foram preenchidos
    const requiredFields = ['firstName', 'lastName', 'email', 'password', 'confirm', 'nif', 'phone'];
    for (let field of requiredFields) {
      if (!form[field]) {
        alert(`Please fill in the ${field}`);
        return;
      }
    }

    // Confirma se as passwords coincidem
    if (form.password !== form.confirm) {
      alert("Passwords do not match");
      return;
    }

    // Substitui address vazio por "No address"
    const finalForm = {
      ...form,
      address: form.address?.trim() || 'No address'
    };

    try {
      await AsyncStorage.setItem('user', JSON.stringify(finalForm));
      console.log('User data saved successfully:', finalForm);
      navigation.navigate('EmailConfirmation');
    } catch (error) {
      console.error('Failed to save data.', error);
      alert('Failed to save data'); 
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
      {['firstName','lastName','email','password','confirm','nif','phone','address'].map(field => (
        <TextInput
          key={field}
          style={styles.input}
          placeholder={field.charAt(0).toUpperCase() + field.slice(1)}
          secureTextEntry={field.includes('password') || field.includes("confirm")}
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
    backgroundColor: '#F5F5F5',
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 20
  },
  logo: {
    width: 180,
    height: 100,
    resizeMode: 'contain',
    marginBottom: 20
  },
  form: {
    width: '100%',
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 8,
    elevation: 2
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 6,
    marginBottom: 12,
    paddingHorizontal: 10
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
  link: {
    color: '#000',
    textDecorationLine: 'underline',
    textAlign: 'center',
    marginTop: 10
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
  },
});