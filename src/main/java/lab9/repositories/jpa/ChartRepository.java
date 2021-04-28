package lab9.repositories.jpa;

import lab9.entities.Chart;

class ChartRepository extends JPARepository<Chart> {
	@Override
	protected String getPrefix() {
		return "chart";
	}

	@Override
	protected void persist(Chart item) {
		for (var chartEntry : item.getChartsEntries()) {
			entityManager.persist(chartEntry);
		}
		entityManager.persist(item);
	}
}
