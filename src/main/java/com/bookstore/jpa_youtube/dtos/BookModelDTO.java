package com.bookstore.jpa_youtube.dtos;

import java.util.Set;
import java.util.UUID;

public record BookModelDTO(String title, UUID publisherId, Set<UUID> authorIds, String reviewComment) {
}
