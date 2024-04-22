import React from 'react'
import { Link } from 'react-router-dom'

export default function chudidar() {
  return (
    <>
      <div className='flex justify-center h-screen bg-gray-200'>
        <div className='flex-col justify-center items-center gap-10'>
          <h1 className='text-4xl font-bold'>Chudidar</h1>
          <div className='flex justify-center items-center gap-2'>
            
            <Link
              to='/add-items/'
              className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded'
            >
              Add Items
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}
