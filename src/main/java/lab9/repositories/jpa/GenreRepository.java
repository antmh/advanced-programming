package lab9.repositories.jpa;

import lab9.entities.Genre;

class GenreRepository extends JPARepository<Genre> {
	@Override
	protected String getPrefix() {
		return "genre";
	}
}
