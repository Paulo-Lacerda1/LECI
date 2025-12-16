import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Alert, Image } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function CancelConfirmationScreen({ route, navigation }) {
    const { bookingId } = route.params;


    const confirmCancel = async () => {
        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];

            const updated = all.filter(b => b.id !== bookingId);

            await AsyncStorage.setItem('bookings', JSON.stringify(updated));
             navigation.navigate('Home');
        } catch (e) {
            console.error('Error cancelling booking:', e);
            Alert.alert('Error', 'Could not cancel the booking.');
        }
    };


    return (
        <View style={styles.container}>
            {/* Header fixo */}
            <View style={styles.header}>
                <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
                    <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
                </TouchableOpacity>
                <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
            </View>

            {/* Conteúdo abaixo do header */}
            <View style={styles.content}>
                <Text style={styles.text}>Are you sure you want to cancel this booking?</Text>
                <View style={styles.buttonRow}>
                    <TouchableOpacity style={[styles.button, styles.back]} onPress={() => navigation.goBack()}>
                        <Text style={styles.buttonText}>Go Back</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={[styles.button, styles.confirm]} onPress={confirmCancel}>
                        <Text style={styles.buttonText}>Yes, Cancel</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </View>
    );

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
    },
    header: {
        flexDirection: 'row',
        alignItems: 'center',
        paddingTop: 50,
        paddingHorizontal: 15,
        paddingBottom: 10,
        borderBottomWidth: 1,
        borderColor: '#ddd',
        backgroundColor: '#fff',
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    },

    navIcon: {
        paddingRight: 10,
    },
    backIcon: {
        width: 24,
        height: 24,
        resizeMode: 'contain',
    },
    logo: {
        width: 150,
        height: 50,
        resizeMode: 'contain',
        marginLeft: 'auto',
        marginRight: 'auto',
    },
    text: {
        fontSize: 20,
        textAlign: 'center',
        marginBottom: 30
    },
    buttonRow: {
        flexDirection: 'row'
    },
    button: {
        padding: 15,
        borderRadius: 5,
        marginHorizontal: 10,
        minWidth: 120,
        alignItems: 'center',
    },
    back: {
        backgroundColor: '#ccc'
    },
    confirm: {
        backgroundColor: '#e74c3c'
    },
    buttonText: {
        color: '#fff', fontWeight: '600'
    },
});
