import * as React from 'react';
import AspectRatio from '@mui/joy/AspectRatio';
import Button from '@mui/joy/Button';
import Card from '@mui/joy/Card';
import CardContent from '@mui/joy/CardContent';
import IconButton from '@mui/joy/IconButton';
import Typography from '@mui/joy/Typography';
import BookmarkAdd from '@mui/icons-material/BookmarkAddOutlined';
import { AppContext } from '../context';
import { useNavigate } from 'react-router-dom';

export default function CardPalette({ filterItems }) {
  
  const name = filterItems[0].name;

  return (
  <div className='p-10'>
    <Card>
      <div>
          <Typography level='title-lg'>{ name }</Typography>
      </div>
      <AspectRatio minHeight='120px' maxHeight='200px'>
        <img
          src='https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286'
          srcSet='https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286&dpr=2 2x'
          loading='lazy'
          alt=''
        />
      </AspectRatio>
    </Card>
  </div>
  );
}