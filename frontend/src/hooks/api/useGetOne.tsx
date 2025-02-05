import { AxiosRequestHeaders, AxiosResponse } from 'axios';
import { useState, useEffect } from 'react';
import axiosInstance from '@/hooks/api/axiosInstance';

type Props = {
  url: string;
  headers?: null | AxiosRequestHeaders;
};

function useGetOne<T>({ url, headers }: Props): T {
  const [data, setData] = useState<null | T>(null);

  const fetchData = async () => {
    const { data }: AxiosResponse<T> = await axiosInstance.get(url, {
      headers,
    });

    return data;
  };

  useEffect(() => {
    fetchData()
      .then((data) => {
        !!data && setData(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return data;
}
export default useGetOne;
