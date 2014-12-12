<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2 style="color: rgb(154, 205, 50);text-align: center;">Warplanes</h2>
				<table border="1" style="margin: auto;text-align: center;font-size: 20;">
					<tr bgcolor="#9acd32">
						<th rowspan="2">Model</th>
						<th rowspan="2">Origin</th>
						<th colspan="4">Chars</th>
						<th colspan="3">Parameters</th>
						<th rowspan="2">Price</th>
					</tr>
					<tr bgcolor="#9acd32">
						<td>Type</td>
						<td>Seats</td>
						<td>Combat kit</td>
						<td>Availability of radar</td>
						<td>Length</td>
						<td>Width</td>
						<td>Height</td>
					</tr>
					<xsl:for-each select="plane/warplane">
						<xsl:sort select="price" />
						<tr>
							<td>
								<xsl:value-of select="model" />
							</td>
							<td>
								<xsl:value-of select="origin" />
							</td>

							<xsl:choose>
								<xsl:when test="chars/type = 'destroyer'">
									<td bgcolor="#0f0">
										<xsl:value-of select="chars/type" />
									</td>
								</xsl:when>
								<xsl:otherwise>
									<td>
										<xsl:value-of select="chars/type" />
									</td>
								</xsl:otherwise>
							</xsl:choose>

							<td>
								<xsl:value-of select="chars/seats" />
							</td>
							<td>
								<xsl:value-of select="chars/combat_kit" />
							</td>
							<xsl:if test="chars/availability_of_radar = 'false'">
								<td>Is missing</td>
							</xsl:if>
							<xsl:if test="chars/availability_of_radar = 'true'">
								<td>Is present</td>
							</xsl:if>
							<td>
								<xsl:value-of select="parameters/length" />
							</td>
							<td>
								<xsl:value-of select="parameters/width" />
							</td>
							<td>
								<xsl:value-of select="parameters/height" />
							</td>
							<td>
								<xsl:value-of select="price" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>