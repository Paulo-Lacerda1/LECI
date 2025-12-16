// screens/BookingSummaryScreen.js
import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity,
    Alert,
    ScrollView,
    Image,
    Dimensions,
} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function BookingSummaryScreen({ route, navigation }) {

    const {
        id,
        title,
        store,
        date,
        vehicleType,
        selectedExtras,
        total,
    } = route.params;

    const generateBookingId = () => {
        return Date.now().toString() + Math.floor(Math.random() * 10000).toString();
    };

    const bookingId = id || generateBookingId();

    const parsedDate = new Date(date);

    const handleConfirm = async () => {
        const booking = {
            id: bookingId,
            title,
            store: 'Thompson Car Service',
            date,
            vehicleType,
            selectedExtras,
            total,
            completed: false,
        };

        try {
            const stored = await AsyncStorage.getItem('bookings');
            const bookings = stored ? JSON.parse(stored) : [];
            bookings.push(booking);
            await AsyncStorage.setItem('bookings', JSON.stringify(bookings));

            navigation.navigate('Home');
        } catch (error) {
            console.error('Error saving appointment:', error);
        }
    };

    const handleCancel = () => {
        navigation.goBack();
    };

    return (
        <View style={styles.container}>
            <ScrollView contentContainerStyle={styles.content}>
                <Image
                    source={require('../assets/logo_transparente.png')}
                    style={styles.logo}
                />

                <TouchableOpacity onPress={handleCancel} style={styles.navIcon}>
                    <Image
                        source={require('../assets/arrowleft.png')}
                        style={styles.backIcon}
                    />
                </TouchableOpacity>

                <Text style={styles.heading}>Confirm your reservation</Text>

                <View style={styles.row}>
                    <Text style={styles.label}>Service:</Text>
                    <Text style={styles.value}>{title}</Text>
                </View>
                <View style={styles.row}>
                    <Text style={styles.label}>Provider:</Text>
                    <Text style={styles.value}>Thompson Car Service</Text>
                </View>
                <View style={styles.row}>
                    <Text style={styles.label}>Date:</Text>
                    <Text style={styles.value}>
                        {parsedDate.toLocaleDateString()} at{' '}
                        {parsedDate.toLocaleTimeString([], {
                            hour: '2-digit',
                            minute: '2-digit',
                        })}
                    </Text>
                </View>
                <View style={styles.row}>
                    <Text style={styles.label}>Vehicle:</Text>
                    <Text style={styles.value}>{vehicleType}</Text>
                </View>

                <Text style={[styles.label, { marginTop: 15 }]}>Extras:</Text>
                {selectedExtras.length > 0 ? (
                    selectedExtras.map((ex, i) => (
                        <Text key={i} style={styles.extraItem}>
                            • {ex.label} (+{ex.price}€)
                        </Text>
                    ))
                ) : (
                    <Text style={styles.value}>None</Text>
                )}

                <View style={[styles.row, { marginTop: 20 }]}>
                    <Text style={styles.totalLabel}>Total:</Text>
                    <Text style={styles.totalValue}>{total.toFixed(2)}€</Text>
                </View>
            </ScrollView>

            {/* Footer com botões fixed */}
            <View style={styles.footer}>
                <TouchableOpacity
                    style={[styles.button, styles.cancelButton]}
                    onPress={handleCancel}
                >
                    <Text style={styles.buttonText}>Back</Text>
                </TouchableOpacity>
                <TouchableOpacity
                    style={[styles.button, styles.confirmButton]}
                    onPress={handleConfirm}
                >
                    <Text style={styles.buttonText}>Confirm</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}

const { width } = Dimensions.get('window');

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
    },
    content: {
        padding: 20,
        paddingBottom: 120,
    },
    logo: {
        width: 180,
        height: 80,
        resizeMode: 'contain',
        alignSelf: 'center',
        marginVertical: 10,
    },
    navIcon: {
        position: 'absolute',
        top: 20,
        left: 20,
    },
    backIcon: {
        width: 24,
        height: 24,
    },
    heading: {
        fontSize: 22,
        fontWeight: 'bold',
        marginBottom: 25,
        textAlign: 'center',
        marginTop: 40,
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginVertical: 8,
    },
    label: {
        fontSize: 16,
        fontWeight: '600',
    },
    value: {
        fontSize: 16,
        color: '#333',
        flexShrink: 1,
        textAlign: 'right',
    },
    extraItem: {
        marginLeft: 10,
        fontSize: 14,
        color: '#555',
        marginTop: 4,
    },
    totalLabel: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    totalValue: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    footer: {
        position: 'absolute',
        bottom: 0,
        width,
        flexDirection: 'row',
        backgroundColor: '#fff',
        padding: 10,
        borderTopWidth: 1,
        borderColor: '#ddd',
    },
    button: {
        flex: 1,
        padding: 14,
        borderRadius: 6,
        alignItems: 'center',
    },
    cancelButton: {
        backgroundColor: '#a9a9a9',
        marginRight: 10,
    },
    confirmButton: {
        backgroundColor: '#000',
    },
    buttonText: {
        color: '#fff',
        fontSize: 16,
        fontWeight: '600',
    },
});