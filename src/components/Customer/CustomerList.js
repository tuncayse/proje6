import React, { useState, useEffect } from 'react';
import { CustomerService } from '../../services/CustomerService';

function CustomerList() {
    const [customers, setCustomers] = useState([]);
    const [filteredCustomers, setFilteredCustomers] = useState([]);
    const [newCustomer, setNewCustomer] = useState({ name: '', mail: '', address: '', city: '', phone: '' });
    const [customerId, setCustomerId] = useState('');
    const [editingCustomer, setEditingCustomer] = useState(null);
    const [editMode, setEditMode] = useState(false); 
    const [customerDetails, setCustomerDetails] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        fetchCustomers();
    }, []);

    const fetchCustomers = async () => {
        try {
            const fetchedCustomers = await CustomerService.getAllCustomers();
            setCustomers(fetchedCustomers);
            setFilteredCustomers(fetchedCustomers);
        } catch (error) {
            console.error('Müşteriler alınırken bir hata oluştu:', error);
        }
    };

    const handleAddCustomer = async () => {
        try {
            await CustomerService.createCustomer(newCustomer);
            setNewCustomer({ name: '', phone: '', mail: '', address: '', city: '' });
            fetchCustomers();
        } catch (error) {
            console.error('Müşteri eklenirken bir hata oluştu:', error);
        }
    };

    const handleUpdateCustomer = async () => {
        try {
            await CustomerService.updateCustomer(editingCustomer.id, editingCustomer);
            setEditingCustomer(null);
            setEditMode(false);
            fetchCustomers(); 
        } catch (error) {
            console.error('Müşteri güncellenirken bir hata oluştu:', error);
        }
    };

    const handleEditClick = (customer) => {
        setEditingCustomer({ ...customer });
        setEditMode(true);
    };

    const handleDelete = async (id) => {
        try {
            await CustomerService.deleteCustomer(id);
            fetchCustomers();
        } catch (error) {
            console.error('Müşteri silinirken bir hata oluştu:', error);
        }
    };

    const handleGetByIdSubmit = async () => {
        try {
            const customer = await CustomerService.getCustomerById(customerId);
            if (customer) {
                setCustomerDetails(customer);
            } else {
                // Müşteri bulunamazsa, önceki müşteri detaylarını temizle
                setCustomerDetails(null);
            }
        } catch (error) {
            console.error('Müşteri alınırken bir hata oluştu:', error);
            setCustomerDetails(null); // Hata oluşursa da detayları temizle
        }
    };
    

    const handleSearch = () => {
        if (!searchTerm) {
            setFilteredCustomers(customers);
        } else {
            const filtered = customers.filter(customer =>
                customer.name.toLowerCase().includes(searchTerm.toLowerCase())
            );
            setFilteredCustomers(filtered);
        }
    };

    return (
        <div className="container">
            <h1 className="mt-3 text-center" style={{ color: "green" }}>Müşteri Listesi</h1>

            {/* Yeni müşteri ekleme işlemi */}
            <div className="mb-3">
                <input type="text" value={newCustomer.name} onChange={(e) => setNewCustomer({ ...newCustomer, name: e.target.value })} placeholder="Name" />
                <input type="text" value={newCustomer.mail} onChange={(e) => setNewCustomer({ ...newCustomer, mail: e.target.value })} placeholder="Email" />
                <input type="text" value={newCustomer.address} onChange={(e) => setNewCustomer({ ...newCustomer, address: e.target.value })} placeholder="Address" />
                <input type="text" value={newCustomer.city} onChange={(e) => setNewCustomer({ ...newCustomer, city: e.target.value })} placeholder="City" />
                <input type="text" value={newCustomer.phone} onChange={(e) => setNewCustomer({ ...newCustomer, phone: e.target.value })} placeholder="Phone" />
                <button className="btn btn-primary mx-2" onClick={handleAddCustomer}>Create Customer</button>
            </div>

           
            {/* Müşteri arama işlemi */}
            <div className="mb-3">
                <input 
                    type="text" 
                    value={searchTerm} 
                    onChange={(e) => setSearchTerm(e.target.value)} 
                    placeholder="Müşteri ismine göre ara" 
                />
                <button onClick={handleSearch} className="btn btn-primary mx-2">Ara</button>
            </div>

            {/* Müşteri Listesi */}
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">İsim</th>
                        <th scope="col">Email</th>
                        <th scope="col">Adres</th>
                        <th scope="col">Şehir</th>
                        <th scope="col">Telefon</th>
                        <th scope="col">İşlemler</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredCustomers.map(customer => (
                        <tr key={customer.id}>
                            <td>{customer.id}</td>
                            <td>{editMode && editingCustomer && editingCustomer.id === customer.id ? (
                                <input
                                    type="text"
                                    value={editingCustomer.name}
                                    onChange={(e) => setEditingCustomer({ ...editingCustomer, name: e.target.value })}
                                />
                            ) : (
                                customer.name
                            )}</td>
                            <td>{editMode && editingCustomer && editingCustomer.id === customer.id ? (
                                <input
                                    type="text"
                                    value={editingCustomer.mail}
                                    onChange={(e) => setEditingCustomer({ ...editingCustomer, mail: e.target.value })}
                                />
                            ) : (
                                customer.mail
                            )}</td>
                            <td>{editMode && editingCustomer && editingCustomer.id === customer.id ? (
                                <input
                                    type="text"
                                    value={editingCustomer.address}
                                    onChange={(e) => setEditingCustomer({ ...editingCustomer, address: e.target.value })}
                                />
                            ) : (
                                customer.address
                            )}</td>
                            <td>{editMode && editingCustomer && editingCustomer.id === customer.id ? (
                                <input
                                    type="text"
                                    value={editingCustomer.city}
                                    onChange={(e) => setEditingCustomer({ ...editingCustomer, city: e.target.value })}
                                />
                            ) : (
                                customer.city
                            )}</td>
                            <td>{editMode && editingCustomer && editingCustomer.id === customer.id ? (
                                <input
                                    type="text"
                                    value={editingCustomer.phone}
                                    onChange={(e) => setEditingCustomer({ ...editingCustomer, phone: e.target.value })}
                                />
                            ) : (
                                customer.phone
                            )}</td>
                            <td>
                                {!editMode || (editMode && editingCustomer && editingCustomer.id !== customer.id) ? (
                                    <button className="btn btn-warning" onClick={() => handleEditClick(customer)}>Edit</button>
                                ) : (
                                    <>
                                        <button className="btn btn-success" onClick={handleUpdateCustomer}>Save</button>
                                        <button className="btn btn-secondary mx-2" onClick={() => setEditMode(false)}>Cancel</button>
                                    </>
                                )}
                                <button className="btn btn-danger" onClick={() => handleDelete(customer.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

             {/* ID'ye göre müşteri getirme işlemi */}
             <div className="mb-3">
                <input type="text" value={customerId} onChange={(e) => setCustomerId(e.target.value)} placeholder="Customer ID" />
                <button className="btn btn-primary mx-2" onClick={handleGetByIdSubmit}>Get Customer by ID</button>
            </div>


            {/* Müşteri Detayları */}
            {customerDetails && (
                <div>
                    <h2>Müşteri Detayları:</h2>
                    <p><strong>ID:</strong> {customerDetails.id}</p>
                    <p><strong>İsim:</strong> {customerDetails.name}</p>
                    <p><strong>Email:</strong> {customerDetails.mail}</p>
                    <p><strong>Adres:</strong> {customerDetails.address}</p>
                    <p><strong>Şehir:</strong> {customerDetails.city}</p>
                    <p><strong>Telefon:</strong> {customerDetails.phone}</p>
                </div>
            )}
        </div>
    );
}

export default CustomerList;
