// screens/BookingScreen.js
import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, Image, ScrollView, TouchableOpacity, Platform, Button, Dimensions, } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import { Picker } from '@react-native-picker/picker';
import { Ionicons } from '@expo/vector-icons';

const VEHICLE_TYPES = ['Small', 'Medium', 'Large'];
const { width } = Dimensions.get('window');

export default function BookingScreen({ route, navigation }) {
    const { service } = route.params;

    const [date, setDate] = useState(new Date());
    const [showDate, setShowDate] = useState(false);
    const [showTime, setShowTime] = useState(false);
    const [vehicle, setVehicle] = useState('');
    const [selectedExtras, setSelectedExtras] = useState([]);

    const extrasTotal = selectedExtras.reduce((sum, ex) => sum + ex.price, 0);
    const total = service.price + extrasTotal;

    const toggleExtra = (extra) => {
        setSelectedExtras((prev) =>
            prev.includes(extra)
                ? prev.filter((e) => e !== extra)
                : [...prev, extra]
        );
    };

    const onConfirm = () => {
        navigation.navigate('BookingSummaryScreen', {
            title: service.name,
            store: service.store,
            date: date.toISOString(),
            vehicleType: vehicle,
            selectedExtras,
            total,
        });
    };

    return (

        <View style={styles.container}>
            <ScrollView contentContainerStyle={styles.content}>
                {/* Header */}
                <Image
                    source={require('../assets/logo_transparente.png')}
                    style={styles.logo}
                />

                <TouchableOpacity
                    onPress={() => navigation.goBack()}
                    style={styles.navIcon}
                >
                    <Image
                        source={require('../assets/arrowleft.png')}
                        style={styles.backIcon}
                    />
                </TouchableOpacity>

                {/* Imagem e título */}
                <Image source={service.image} style={styles.image} />
                <Text style={styles.serviceName}>{service.name}</Text>
                <Text style={styles.description}>{service.description}</Text>

              <View style={styles.row}>
                <Text style={styles.label}>Select date</Text>
                {Platform.OS === 'web' ? (
                    <input
                        type="date"
                        value={date.toISOString().split('T')[0]}
                        onChange={(e) => {
                            const selectedDate = new Date(e.target.value);
                            selectedDate.setHours(date.getHours(), date.getMinutes());
                            setDate(selectedDate);
                        }}
                        style={{
                            flex: 1,
                            padding: 10,
                            backgroundColor: '#eee',
                            borderRadius: 5,
                            textAlign: 'right',
                        }}
                    />
                ) : (
                        <>
                            <TouchableOpacity
                                style={styles.input}
                                onPress={() => setShowDate(true)}
                            >
                                <Text>{date.toLocaleDateString()}</Text>
                            </TouchableOpacity>
                            {showDate && (
                                <DateTimePicker
                                    value={date}
                                    mode="date"
                                    display={Platform.OS === 'android' ? 'calendar' : 'default'}
                                    onChange={(e, d) => {
                                        setShowDate(Platform.OS === 'ios');
                                        if (d) {
                                            const nd = new Date(d);
                                            nd.setHours(date.getHours(), date.getMinutes());
                                            setDate(nd);
                                        }
                                    }}
                                />
                            )}
                        </>
                    )}
            </View>


                {/* Hora */}
                <View style={styles.row}>
                <Text style={styles.label}>Select time</Text>
                {Platform.OS === 'web' ? (
                    <input
                        type="time"
                        value={date.toTimeString().substring(0, 5)}
                        onChange={(e) => {
                            const [hours, minutes] = e.target.value.split(':');
                            const newDate = new Date(date);
                            newDate.setHours(+hours);
                            newDate.setMinutes(+minutes);
                            setDate(newDate);
                        }}
                        style={{
                            flex: 1,
                            padding: 10,
                            backgroundColor: '#eee',
                            borderRadius: 5,
                            textAlign: 'right',
                        }}
                    />
                ) : (
                    <>
                        <TouchableOpacity
                            style={styles.input}
                            onPress={() => setShowTime(true)}
                        >
                            <Text>
                                {date.toLocaleTimeString([], {
                                    hour: '2-digit',
                                    minute: '2-digit',
                                })}
                            </Text>
                        </TouchableOpacity>
                        {showTime && (
                            <DateTimePicker
                                value={date}
                                mode="time"
                                display="default"
                                onChange={(e, d) => {
                                    setShowTime(Platform.OS === 'ios');
                                    if (d) {
                                        const nd = new Date(date);
                                        nd.setHours(d.getHours(), d.getMinutes());
                                        setDate(nd);
                                    }
                                }}
                            />
                        )}
                    </>
                )}
            </View>


                {/* Dropdown de Tipo de veículo */}
                <Text style={styles.label}>Vehicle type</Text>
                <View style={styles.pickerContainer}>
                    <Picker
                        selectedValue={vehicle}
                        onValueChange={(val) => setVehicle(val)}
                        style={styles.picker}
                    >
                        <Picker.Item label="Select vehicle type..." value="" />
                        {VEHICLE_TYPES.map((type) => (
                            <Picker.Item key={type} label={type} value={type} />
                        ))}
                    </Picker>
                </View>

                {/* Extras */}
                <Text style={[styles.label, { marginTop: 20 }]}>Extras</Text>
                {service.extras.map((ex) => {
                    const isSelected = selectedExtras.includes(ex);
                    return (
                        <TouchableOpacity
                            key={ex.label}
                            style={[styles.option, isSelected && styles.optionSelected]}
                            onPress={() => toggleExtra(ex)}
                        >
                            <Text style={isSelected ? styles.optionTextSelected : styles.optionText}>
                                {ex.label} (+{ex.price}€)
                            </Text>
                        </TouchableOpacity>
                    );
                })}

                {/* Espaço extra para não colidir com o footer */}
                <View style={{ height: 100 }} />
            </ScrollView>

            {/* Footer fixo */}
            <View style={styles.footer}>
                <Text style={styles.totalText}>Total: {total.toFixed(2)}€</Text>
                <TouchableOpacity
                    style={[
                        styles.confirmButton,
                        !(vehicle && date) && { backgroundColor: '#888' },
                    ]}
                    disabled={!vehicle}
                    onPress={onConfirm}
                >
                    <Text style={styles.confirmText}>Confirm reservation</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff'
    },
    content: {
        padding: 20,
        paddingBottom: 0
    },
    logo: {
        width: 180,
        height: 80,
        resizeMode: 'contain',
        alignSelf: 'center',
        marginBottom: 10
    },
    navIcon: {
        position: 'absolute',
        top: 30,
        left: 20
    },
    backIcon: {
        width: 24,
        height: 24
    },
    image: {
        width: '100%',
        height: 200,
        borderRadius: 8,
        marginBottom: 10
    },
    serviceName: {
        fontSize: 20,
        fontWeight: 'bold'
    },
    description: {
        fontSize: 14,
        color: '#333',
        marginBottom: 20
    },
    row: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 15
    },
    label: {
        flex: 1,
        fontWeight: '600'
    },
    input: {
        flex: 1,
        padding: 10,
        backgroundColor: '#eee',
        borderRadius: 5,
        alignItems: 'flex-end',
    },
    pickerContainer: {
        backgroundColor: '#eee',
        borderRadius: 5,
        marginVertical: 10,
    },
    picker: {
        height: 50,
        width: '100%',
        padding: 10,
    },
    option: {
        padding: 10,
        backgroundColor: '#eee',
        borderRadius: 5,
        marginVertical: 5
    },
    optionSelected: {
        backgroundColor: '#333'
    },
    optionText: {
        color: '#000'
    },
    optionTextSelected: {
        color: '#fff'
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
    totalText: {
        fontSize: 18,
        fontWeight: 'bold'
    },
    confirmButton: {
        backgroundColor: '#000',
        paddingVertical: 12,
        paddingHorizontal: 20,
        borderRadius: 5,
    },
    confirmText: {
        color: '#fff',
        fontWeight: '600',
    },
});