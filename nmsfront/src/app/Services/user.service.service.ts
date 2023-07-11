import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { Product } from '../model/product';
import { OrderDetails } from '../model/order-details';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080';

  public userSignUp(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/user/signup`, user);
  }

  public addMovie(product: Product, image: Blob): Observable<any> {
    let formData = new FormData();
    formData.append('product', JSON.stringify(product));
    formData.append('image', image);
    return this.http.post<any>(`${this.baseUrl}/add/product`, formData);
  }

  public getAllMovies(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all-products`);
  }

  public getMovieByName(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/products/${name}`);
  }

  public getMovieByCategory(category: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/products-by-category/${category}`);
  }

  public deleteMovie(pid: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/product/${pid}`);
  }

  public findById(pid: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get-product/${pid}`);
  }

  public updateMovie(pid: number, product: Product): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/update/product/${pid}`, product);
  }

  public setAvailable(pid: number, product: Product): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/set-availability/product/${pid}`, product);
  }

  public createOrder(orderDetails: OrderDetails): Observable<OrderDetails> {
    return this.http.post<OrderDetails>(`${this.baseUrl}/user/create/order`, orderDetails);
  }

  public getOrderById(oid: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get/order-invoice/${oid}`);
  }

  public getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all/orders`);
  }

  public deleteOrder(oid: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/delete/order/${oid}`);
  }

  public getOrderByUsername(username: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/orders/${username}`);
  }

}
