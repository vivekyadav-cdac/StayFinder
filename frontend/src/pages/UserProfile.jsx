// src/pages/UserProfile.js
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getUser, editUser } from '../features/user/userSlice';

export default function UserProfile() {
  const dispatch = useDispatch();
  const { user, loading, error } = useSelector((state) => state.user);

  const userId = localStorage.getItem('userId');
  const [edit, setEdit] = useState(false);
  const [editData, setEditData] = useState({});
  const [passwordData, setPasswordData] = useState({ password: '' });
  const [message, setMessage] = useState('');

  useEffect(() => {
    if (userId) {
      dispatch(getUser(userId));
    }
  }, [dispatch, userId]);

  useEffect(() => {
    console.log('User data:', user);
    if (user) {
      setEditData({
        email: user.email || '',
        firstName: user.firstName || '',
        lastName: user.lastName || '',
        phone: user.phone || '',
      });
    }
  }, [user]);

  const isEditable = user?.role === 'TENANT' || user?.role === 'OWNER';

  const handleEdit = () => setEdit(true);

  const handleEditChange = (e) => {
    setEditData({ ...editData, [e.target.name]: e.target.value });
  };

  const submitEdit = () => {
    console.log('Submitting edit:', editData  );
    
    dispatch(editUser({ userId, userData: editData }))
      .unwrap()
      .then(() => {
        setEdit(false);
        setMessage('Updated successfully');
      })
      .catch(() => setMessage('Update failed'));
  };

  const handlePasswordChange = (e) => {
    setPasswordData({ ...passwordData, [e.target.name]: e.target.value });
  };

  const submitPassword = () => {
    const updatedData = { ...editData, password: passwordData.password };
    dispatch(editUser({ userId, userData: updatedData }))
      .unwrap()
      .then(() => setMessage('Password changed successfully!'))
      .catch(() => setMessage('Failed to change password'));
  };

  if (loading) return <div>Loading...</div>;
  if (!user) return <div>No user found.</div>;

  return (
    <div style={{ maxWidth: 500, margin: '2em auto', padding: '1em', border: '1px solid #eee', borderRadius: 8 }}>
      <h2>User Profile</h2>
      <div><b>Role:</b> {user.role}</div>
      <div><b>Email:</b> {user.email}</div>
      {edit ? (
        <>
          <div>
            <b>First Name:</b>
            <input name="firstName" value={editData.firstName} onChange={handleEditChange} />
          </div>
          <div>
            <b>Last Name:</b>
            <input name="lastName" value={editData.lastName} onChange={handleEditChange} />
          </div>
          <div>
            <b>Phone:</b>
            <input name="phone" value={editData.phone} onChange={handleEditChange} />
          </div>
          <button onClick={submitEdit}>Save</button>
          <button onClick={() => setEdit(false)}>Cancel</button>
        </>
      ) : (
        <>
          <div><b>First Name:</b> {user.firstName}</div>
          <div><b>Last Name:</b> {user.lastName}</div>
          <div><b>Phone:</b> {user.phone}</div>
          {isEditable && <button onClick={handleEdit}>Edit Details</button>}
        </>
      )}

      <hr />
      <h3>Change Password</h3>
      <input
        type="password"
        name="password"
        placeholder="New password"
        value={passwordData.password}
        onChange={handlePasswordChange}
      />
      <button onClick={submitPassword}>Change Password</button>
      {message && <div style={{ marginTop: '1em', color: 'green' }}>{message}</div>}
      {error && <div style={{ marginTop: '1em', color: 'red' }}>{error}</div>}
    </div>
  );
}
