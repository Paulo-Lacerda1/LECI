import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity,
    Alert,
    Image,
    Dimensions,
    ScrollView,
} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

const { width } = Dimensions.get('window');

export default function BookingDetailsScreen({ route, navigation }) {
    const { booking, index } = route.params;

    /*const handleCancel = async () => {
        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];
            all.splice(index, 1); // remove agendamento
            await AsyncStorage.setItem('bookings', JSON.stringify(all));
            navigation.goBack();
        } catch (e) {
            console.error('Error cancelling booking:', e);
            Alert.alert('Error', 'Could not cancel the booking.');
        }
    };

    const handleComplete = async () => {
        try {
            const stored = await AsyncStorage.getItem('bookings');
            const all = stored ? JSON.parse(stored) : [];
            all[index] = { ...all[index], completed: true };
            await AsyncStorage.setItem('bookings', JSON.stringify(all));
            navigation.navigate('ReviewScreen', { booking: all[index] });
        } catch (e) {
            console.error('Error completing booking:', e);
            Alert.alert('Error', 'Could not complete the booking.');
        }
    };*/

    return (
        <View style={styles.container}>
            {/* Header fixo */}
            <View style={styles.header}>
                <TouchableOpacity onPress={() => navigation.goBack()} style={styles.navIcon}>
                    <Image source={require('../assets/arrowleft.png')} style={styles.backIcon} />
                </TouchableOpacity>
                <Image source={require('../assets/logo_transparente.png')} style={styles.logo} />
            </View>

            {/* Conteúdo com scroll */}
            <ScrollView contentContainerStyle={styles.scrollContent}>
                <Text style={styles.heading}>Booking Details</Text>
                <Text style={styles.item}>Service: {booking.title}</Text>
                <Text style={styles.item}>Provider: {booking.store}</Text>
                <Text style={styles.item}>
                    Date: {new Date(booking.date).toLocaleDateString()} at{' '}
                    {new Date(booking.date).toLocaleTimeString([], {
                        hour: '2-digit',
                        minute: '2-digit',
                    })}h
                </Text>
                <Text style={styles.item}>Vehicle: {booking.vehicleType}</Text>
                <Text style={styles.item}>
                    Extras: {booking.selectedExtras.length
                        ? booking.selectedExtras.map(e => e.label).join(', ')
                        : 'None'}
                </Text>
            </ScrollView>

            {/* Footer fixo */}
            <View style={styles.footer}>
                <TouchableOpacity
                    style={[styles.button, styles.cancelButton]}
                    onPress={() => navigation.navigate('CancelConfirmationScreen', { bookingId: booking.id })}
                >
                    <Text style={styles.buttonText}>Cancel Booking</Text>
                </TouchableOpacity>

                <TouchableOpacity
                    style={[styles.button, styles.completeButton]}
                    onPress={() => navigation.navigate('CompleteScreen', { booking, index })}
                >
                    <Text style={styles.buttonText}>Complete & Review</Text>
                </TouchableOpacity>

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
    scrollContent: {
        padding: 20,
        paddingBottom: 100,
    },
    heading: {
        fontSize: 22,
        fontWeight: 'bold',
        marginBottom: 20,
        textAlign: 'center',
    },
    item: {
        fontSize: 16,
        marginBottom: 10,
    },
    footer: {
        position: 'absolute',
        bottom: 0,
        width,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        backgroundColor: '#fff',
        padding: 15,
        borderTopWidth: 1,
        borderColor: '#ddd',
    },
    button: {
        flex: 1,
        paddingVertical: 12,
        borderRadius: 5,
        alignItems: 'center',
    },
    cancelButton: {
        backgroundColor: '#a9a9a9',
        marginRight: 10,
    },
    completeButton: {
        backgroundColor: '#000',
        marginLeft: 10,
    },
    buttonText: {
        color: '#fff',
        fontWeight: '600',
    },
});
