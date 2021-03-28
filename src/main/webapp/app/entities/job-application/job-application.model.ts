export interface IJobApplication {
  id?: number;
  firstName?: string | null;
  lastName?: string | null;
  age?: number | null;
  gender?: string | null;
  educationStatus?: string | null;
  city?: string | null;
  district?: string | null;
  militaryStatus?: string | null;
  language?: string | null;
  programmingLanguage?: string | null;
  hobbies?: string | null;
  certificates?: string | null;
}

export class JobApplication implements IJobApplication {
  constructor(
    public id?: number,
    public firstName?: string | null,
    public lastName?: string | null,
    public age?: number | null,
    public gender?: string | null,
    public educationStatus?: string | null,
    public city?: string | null,
    public district?: string | null,
    public militaryStatus?: string | null,
    public language?: string | null,
    public programmingLanguage?: string | null,
    public hobbies?: string | null,
    public certificates?: string | null
  ) {}
}

export function getJobApplicationIdentifier(jobApplication: IJobApplication): number | undefined {
  return jobApplication.id;
}
