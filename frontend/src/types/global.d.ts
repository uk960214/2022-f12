import { ValueOf as _ValueOf } from '@team-f12/f12-util-types';

declare global {
  type ValueOf<T> = _ValueOf<T>;
}
