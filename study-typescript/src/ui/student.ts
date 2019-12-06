import { Person } from '../data/person';

export class Student extends Person {
  study(): string {
    return `${this.name} is studying.`;
  }
}

