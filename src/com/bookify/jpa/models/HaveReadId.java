/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.models;

import java.io.Serializable;

/**
 * Used so {@link HaveRead} can have 2 primary keys.
 */

public class HaveReadId implements Serializable {
    private int userId;
    private Book book;
}
