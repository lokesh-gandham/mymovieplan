import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

export class cuisineservice{
    baseUrl = 'http://localhost:8080';
    constructor(private httpclient:HttpClient)
    {}
    public getAllCuisines():Observable<any[]>{
        return this.httpclient.get<any[]>(`${this.baseUrl}/cuisines/all`);
    }
}