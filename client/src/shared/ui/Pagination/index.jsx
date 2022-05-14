import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

export default function PaginationRounded({size, setActivePage}) {

	const onHandleChange = (e, value) => {
		setActivePage(value);
	}
	
	return (
		<Stack sx={{position: 'absolute', bottom: '100px', left: '50%', transform: 'translateX(-50%)'}} spacing={2}>
			<Pagination onChange={onHandleChange} count={size} variant="outlined" shape="rounded" />
		</Stack>
	);
}