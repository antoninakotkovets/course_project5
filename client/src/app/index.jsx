import Appbar from '../shared/ui/AppBar';
import Pages from '../pages';
import Providers from './Providers'

import './index.css';

function App() {
		
	return (	
		<Providers>
			<div className="App">
				<Appbar/>
				<Pages/>
				
			</div>

		</Providers>
	);
}

export default App;
