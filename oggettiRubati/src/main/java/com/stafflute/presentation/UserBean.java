/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.stafflute.presentation;

import com.stafflute.application.UserService;
import com.stafflute.entities.user.Utente;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UserBean {

    @Inject
    private UserService bookService;
    private List<Utente> booksAvailable;
    private String bookTitle;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public List<Utente> getBooksAvailable() {
        return booksAvailable;
    }

    public void setBooksAvailable(List<Utente> booksAvailable) {
        this.booksAvailable = booksAvailable;
    }

    public String fetchBooks()
    {
        //booksAvailable=bookService.getAllBooks();
        return "success";
    }

    public String add()
    {
        Utente book = new Utente();
        book.setUsername(bookTitle);
        //bookService.addBook(book);
        return "success";
    }
    
    public Utente getBook(Integer id) {
    	return null; //bookService.getBook(id);
    }
}
