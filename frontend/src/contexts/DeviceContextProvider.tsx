import { Breakpoints } from '@/types/styled';
import { createContext, PropsWithChildren, useEffect, useState } from 'react';

import { breakpoints } from '@/style/theme';

export const DeviceContext = createContext<keyof Breakpoints | null>(null);
export const DisplayWidthContext = createContext<number | null>(null);

function DeviceContextProvider({ children }: PropsWithChildren) {
  const [device, setDevice] = useState<keyof Breakpoints | null>(null);
  const [displayWidth, setDisplayWidth] = useState<number | null>(null);

  const handleWindowSize = () => {
    const { innerWidth } = window;
    setDisplayWidth(innerWidth);
    if (innerWidth > breakpoints.tablet) {
      setDevice('desktop');
      return;
    }
    if (innerWidth > breakpoints.mobile) {
      setDevice('tablet');
      return;
    }
    setDevice('mobile');
  };

  useEffect(() => {
    handleWindowSize();
    window.addEventListener('resize', handleWindowSize);
    return () => {
      window.removeEventListener('resize', handleWindowSize);
    };
  }, []);

  return (
    <DeviceContext.Provider value={device}>
      <DisplayWidthContext.Provider value={displayWidth}>
        {children}
      </DisplayWidthContext.Provider>
    </DeviceContext.Provider>
  );
}

export default DeviceContextProvider;
